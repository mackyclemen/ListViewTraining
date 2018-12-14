package com.mackyc.projects.listviewtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String currency = "PHP";
    private int maxTender = 2000;

    CostBreakdownItem[] costBreakdownItems = {

            new CostBreakdownItem(CostBreakdownItem.CATEGORY_ESSENTIALS, 800),
            new CostBreakdownItem(CostBreakdownItem.CATEGORY_FOOD, 400),
            new CostBreakdownItem(CostBreakdownItem.CATEGORY_SCHOOL, 200),
            new CostBreakdownItem(CostBreakdownItem.CATEGORY_OTHER, 200)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CostBreakdownAdapter adapter = new CostBreakdownAdapter(this, costBreakdownItems);
        LinearLayout listView = findViewById(R.id.listView);
        if(costBreakdownItems.length == 0) {
            TextView noItemsPlaceholder = new TextView(this);
            noItemsPlaceholder.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            noItemsPlaceholder.setText("No breakdown yet! Add purchases to see breakdown");
            noItemsPlaceholder.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            listView.addView(noItemsPlaceholder);
        } else {
            LayoutInflater inflater = getLayoutInflater();
            for (int position = 0;
                 position < Math.min(costBreakdownItems.length, 3);
                 position++) {
                View v = inflater.inflate(R.layout.listitem_breakdown, listView, false);
                CostBreakdownItem item = costBreakdownItems[position];
                final TextView categoryName, categoryTender;
                final ProgressBar categoryProgress;

                categoryName = v.findViewById(R.id.categoryName);
                categoryTender = v.findViewById(R.id.categoryTender);
                categoryProgress = v.findViewById(R.id.categoryProgress);

                String category;

                switch (item.getCategory()) {
                    case CostBreakdownItem.CATEGORY_FOOD:
                        category = "Food";
                        break;

                    case CostBreakdownItem.CATEGORY_HEALTH:
                        category = "Health";
                        break;

                    case CostBreakdownItem.CATEGORY_ESSENTIALS:
                        category = "Essentials";
                        break;

                    case CostBreakdownItem.CATEGORY_SCHOOL:
                        category = "School";
                        break;

                    case CostBreakdownItem.CATEGORY_OTHER:
                    default:
                        category = "Other";
                        break;
                }

                categoryName.setText(category);
                categoryTender.setText(String.format("%s%.0f", currency, item.getBreakdownPercentage()));
                categoryProgress.setMax(maxTender);
                categoryProgress.setProgress((int) item.getBreakdownPercentage());

                listView.addView(v);
            }

        }
    }
}
