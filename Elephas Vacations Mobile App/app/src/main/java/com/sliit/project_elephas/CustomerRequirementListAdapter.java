package com.sliit.project_elephas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerRequirementListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomerRequirement> mCustomerRequirementList;

    //constructor


    public CustomerRequirementListAdapter(Context mContext, List<CustomerRequirement> mCustomerRequirementList) {
        this.mContext = mContext;
        this.mCustomerRequirementList = mCustomerRequirementList;
    }

    @Override
    public int getCount() {
        return mCustomerRequirementList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCustomerRequirementList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.customer_requirement_list,null);
        TextView tvCustomerID = (TextView) v.findViewById(R.id.tv_customerID);
        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        TextView tvNationality = (TextView) v.findViewById(R.id.tv_nationality);
        TextView tvPassportNo = (TextView) v.findViewById(R.id.tv_PassportNo);
        TextView tvNoOfPeople = (TextView) v.findViewById(R.id.tv_noOfPeople);
        TextView tvNoOfDays = (TextView) v.findViewById(R.id.tv_noOfDays);
        TextView tvArrivalDate = (TextView) v.findViewById(R.id.tv_arrivalDate);
        TextView tvDepartureDate = (TextView) v.findViewById(R.id.tv_departureDate);
        TextView tvStarCategory = (TextView) v.findViewById(R.id.tv_starCategory);
        TextView tvRemark = (TextView) v.findViewById(R.id.tv_remark);

        //set text for TextView
        String ID = Integer.toString(mCustomerRequirementList.get(position).getId());
        tvCustomerID.setText(ID);
        tvName.setText(mCustomerRequirementList.get(position).getName());
        tvNationality.setText(mCustomerRequirementList.get(position).getNationality());
        tvPassportNo.setText(mCustomerRequirementList.get(position).getPassPortNo());
        tvNoOfPeople.setText(mCustomerRequirementList.get(position).getNoOfPeople());
        tvNoOfDays.setText(mCustomerRequirementList.get(position).getNoOfDays());
        tvArrivalDate.setText(mCustomerRequirementList.get(position).getArrivalDate());
        tvDepartureDate.setText(mCustomerRequirementList.get(position).getDepartureDate());
        tvStarCategory.setText(mCustomerRequirementList.get(position).getStarCategory());
        tvRemark.setText(mCustomerRequirementList.get(position).getRemark());

        //save customer ID to tag
        v.setTag(mCustomerRequirementList.get(position).getPassPortNo());

        return v;
    }
}
