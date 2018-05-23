package com.app.sampleandroidproject.ui.rxexample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.beans.result.DaoSession;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RxDaoActivity extends BaseActivity {

    private EditText editText;
    private View addNoteButton;

    private RxDao<Note, Long> noteDao;
    private RxQuery<Note> notesQuery;
    private NotesAdapter notesAdapter;

    @Override
    protected int getContentResource() {
        return R.layout.activity_rx_dao;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("RxDaoActivity");
        setUpViews();
        // get the Rx variant of the note DAO
        DaoSession daoSession = AppManagers.getDbManagers().getDaoSession();
        noteDao = daoSession.getNoteDao().rx();
        // query all notes, sorted a-z by their text
        notesQuery = daoSession.getNoteDao().queryBuilder().orderAsc(NoteDao.Properties.Text).rx();
        updateNotes();
    }

    @Override
    protected void stopWork() {

    }

    private void updateNotes() {
        notesQuery.list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Note>>() {
                    @Override
                    public void call(List<Note> notes) {
                        notesAdapter.setNotes(notes);
                    }
                });
    }

    protected void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotes);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter(noteClickListener);
        recyclerView.setAdapter(notesAdapter);

        addNoteButton = findViewById(R.id.buttonAdd);

        editText = (EditText) findViewById(R.id.editTextNote);

        //noinspection ConstantConditions
        RxTextView.editorActions(editText).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer actionId) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            addNote();
                        }
                    }
                });
        RxTextView.afterTextChangeEvents(editText).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        boolean enable = textViewAfterTextChangeEvent.editable().length() > 0;
                        addNoteButton.setEnabled(enable);
                    }
                });
    }

    public void onAddButtonClick(View view) {
        addNote();
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = new SimpleDateFormat("HH:mm:ss/yyyy-MM-dd");
        String comment = "Added on " + df.format(new Date());

        Note note = new Note(null, noteText, comment, new Date(), NoteType.TEXT);
        noteDao.insert(note)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Note>() {
                    @Override
                    public void call(Note note) {
                        Log.d("DaoExample", "Inserted new note, ID: " + note.getId());
                        updateNotes();
                    }
                });
    }

    NotesAdapter.NoteClickListener noteClickListener = new NotesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = notesAdapter.getNote(position);
            final Long noteId = note.getId();

            noteDao.deleteByKey(noteId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            Log.d("DaoExample", "Deleted note, ID: " + noteId);
                            updateNotes();
                        }
                    });
        }
    };
}
