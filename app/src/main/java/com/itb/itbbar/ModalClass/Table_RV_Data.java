package com.itb.itbbar.ModalClass;

public class Table_RV_Data {

    private boolean isChecked=false;
    private String text;

    public Table_RV_Data(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
