package com.example.fortrevi.View;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class CustomView extends GridLayout {

    private Context mContext;
    private int mSelectedRow = -1, mSelectedColumn = -1;
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            resetState();
        }
    };
    private TextView[] childTextViews;
    private Button[] childButtons;

    public CustomView(Context context, int row, int column) {
        super(context);
        mContext = context;
        childTextViews = new TextView[row * column];
        childButtons = new Button[column];

        setColumnCount(column);
        setRowCount(row+1);
        setUseDefaultMargins(true);

        for (int i=0; i<row+1; i++){
            for (int j=0; j<column; j++) {
                if (i != row)
                    addCell(i,j);
                else
                    addButton(j);
            }
        }
    }

    private void addCell(int rowPosition, int columnPosition){
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.rowSpec = GridLayout.spec(rowPosition, 1,1f);
        layoutParams.columnSpec = GridLayout.spec(columnPosition, 1, 1f);

        TextView tv = new TextView(mContext);
        tv.setBackgroundColor(Color.LTGRAY);
        tv.setGravity(Gravity.CENTER);

        childTextViews[rowPosition*getColumnCount() + columnPosition] = tv;
        addView(tv, layoutParams);
    }

    private void addButton(int columnPosition){
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.rowSpec = GridLayout.spec(getRowCount() -1, 1,1f);
        layoutParams.columnSpec = GridLayout.spec(columnPosition, 1, 1f);

        Button btn = new Button(mContext);
        btn.setGravity(Gravity.CENTER);
        btn.setText("Confirm");
        btn.setEnabled(false);
        btn.setOnClickListener(mOnClickListener);

        childButtons[columnPosition] = btn;
        addView(btn, layoutParams);
    }

    public void highlight(int selectedRow, int selectedColumn){
        resetState();
        mSelectedRow = selectedRow;
        mSelectedColumn = selectedColumn;
        highlightCell();
        highlightColumn();
        enableButton();
    }

    private void highlightCell(){
        childTextViews[mSelectedRow*getColumnCount() + mSelectedColumn]
                .setText("random");
    }

    public void cancelCellHighlight(){
        childTextViews[mSelectedRow*getColumnCount() + mSelectedColumn]
                .setText(null);
    }

    private void highlightColumn(){
        for (int i=0; i<getRowCount()-1; i++)
            childTextViews[i*getColumnCount() + mSelectedColumn].setBackgroundColor(Color.YELLOW);
    }

    private void cancelColumnHighlight(){
        for (int i=0; i<getRowCount()-1; i++)
        childTextViews[i*getColumnCount() + mSelectedColumn].setBackgroundColor(Color.LTGRAY);
    }

    private void enableButton(){
        childButtons[mSelectedColumn].setEnabled(true);
    }

    private void disableButton(){
        childButtons[mSelectedColumn].setEnabled(false);
    }

    private void resetState(){
        if (mSelectedColumn == -1 || mSelectedRow == -1)
            return;
        cancelCellHighlight();
        cancelColumnHighlight();
        disableButton();
        mSelectedColumn = -1;
        mSelectedRow = -1;
    }
}
