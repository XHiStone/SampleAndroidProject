package com.app.sampleandroidproject.utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @Title: EdittextUtil
 * @Description: 描述
 * @date 2016/10/21 10:56
 * @auther xie
 */
public class EdittextUtil {
    /**
     * 可输入字符长度
     *
     * @param edittext
     * @param maxLength
     */
    public static void editMaxLength(final EditText edittext,
                                     final int maxLength) {
        // 键盘文件输入
        InputFilter inputfilter = new InputFilter() {
            // 字符连发事件
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                int dindex = 0;
                int count = 0;
                while (count <= maxLength && dindex < dest.length()) {
                    char c = dest.charAt(dindex++);
                    if (c < 128) {
                        count = count + 1;
                    } else {
                        count = count + 2;
                    }
                }
                if (count > maxLength) {
                    return dest.subSequence(0, dindex - 1);
                }
                int sindex = 0;
                while (count <= maxLength && sindex < source.length()) {
                    char c = source.charAt(sindex++);
                    if (c < 128) {
                        count = count + 1;
                    } else {
                        count = count + 2;
                    }
                }
                if (count > maxLength) {
                    sindex--;
                }

                return source.subSequence(0, sindex);
            }
        };
        edittext.setFilters(new InputFilter[]{inputfilter});
        /* 禁止输入特殊字符 */
        edittext.addTextChangedListener(new TextWatcher() {
            String tmp = "";
            String digits = " /\\:*?<>|\"\n\t()+-#._,";

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                edittext.setSelection(s.length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                tmp = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (str.equals(tmp)) {
                    return;
                }
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < str.length(); i++) {
                    if (digits.indexOf(str.charAt(i)) < 0) {
                        sb.append(str.charAt(i));
                    }
                }
                tmp = sb.toString();
                edittext.setText(tmp);
            }
        });
    }

    /**
     * 禁止输入特殊字符
     */
    public static void edittextForPwd(final EditText edittext) {
        // TextWatcher文本观察者对文本时刻监听是否改变
        edittext.addTextChangedListener(new TextWatcher() {
            String tmp = "";
            String digits = " /\\:*?<>|\"\n\t()+-#.,;";

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                edittext.setSelection(s.length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                tmp = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (str.equals(tmp)) {
                    return;
                }
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < str.length(); i++) {
                    // 除去digits = " /\\:*?<>|\"\n\t()+-#.,"输出正确的
                    if (digits.indexOf(str.charAt(i)) < 0) {
                        sb.append(str.charAt(i));
                    }
                }
                tmp = EmojiUtils.filterEmoji(sb.toString());
                edittext.setText(tmp);
            }
        });
    }
}
