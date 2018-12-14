package com.mackyc.projects.listviewtraining;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CostBreakdownAdapter extends ArrayAdapter<CostBreakdownItem> {

    private final CostBreakdownItem[] costBreakdownItems;
    private final Context context;

    private int maxTender = 2000; // TODO: 12/14/2018 Set dynamically
    private String currency = "PHP"; // TODO: 12/14/2018 Set dynamically

    public CostBreakdownAdapter(Context context, CostBreakdownItem[] costBreakdownItems) {
        super(context, R.layout.listitem_breakdown, costBreakdownItems);

        this.context = context;
        this.costBreakdownItems = costBreakdownItems;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        CostBreakdownItem costBreakdownItem = costBreakdownItems[position];
        if (convertView == null)
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listitem_breakdown, parent, false);

        final TextView categoryName, categoryTender;
        final ProgressBar categoryProgress;

        categoryName = convertView.findViewById(R.id.categoryName);
        categoryTender = convertView.findViewById(R.id.categoryTender);
        categoryProgress = convertView.findViewById(R.id.categoryProgress);

        String category;

        switch (costBreakdownItem.getCategory()) {
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
        categoryTender.setText(String.format("%s%.0f", currency, costBreakdownItem.getBreakdownPercentage()));
        categoryProgress.setMax(maxTender);
        categoryProgress.setMin((int)costBreakdownItem.getBreakdownPercentage());

        return convertView;
    }

    @Override
    public int getCount() {
        return Math.min(costBreakdownItems.length, 3);
    }
}
