package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("AppCompatCustomView")
public class EmoEditText extends EditText {
    private int cursorPos;
    private String inputAfterText;
    private boolean resetText;
    public EmoEditText(Context context) {
        super(context);
    }

    public EmoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmoEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initEditText(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!resetText){
                    cursorPos = getSelectionEnd();
                    inputAfterText = charSequence.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!resetText){
                    if (i2>=2){
                        CharSequence input = charSequence.subSequence(cursorPos,cursorPos+i2);
                        if (containsEmoji(input.toString())){
                            resetText = true;
                            Toast.makeText(getContext(),"暂不支持表情评论",Toast.LENGTH_SHORT).show();
                            setText(inputAfterText);
                            CharSequence text = getText();
                            if (text instanceof Spannable){
                                Spannable spanText = (Spannable) text;
                                Selection.setSelection(spanText,text.length());
                            }
                        }
                    }else {
                        resetText = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }
}
