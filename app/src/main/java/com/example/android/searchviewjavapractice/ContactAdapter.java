package com.example.android.searchviewjavapractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>  {

    private Context context;
    private List<Contacts> listContacts;
    private ArrayList<Contacts> mArrayList;
    private int layout;



    private AppDatabase mDatabase;

    public ContactAdapter(Context context, ArrayList<Contacts> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
        this.mArrayList=listContacts;
        mDatabase = AppDatabase.getInstance(context);
    }

    public ContactAdapter(Context context, int layout, List<Contacts> listContacts) {
        this.context = context;
        this.layout = layout;
        this.listContacts = listContacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_layout, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {


        final Contacts contacts = listContacts.get(position);
        String name = contacts.getName();
        String phone = contacts.getPhno();

        holder.nameTextView.setText(name);
        holder.phoneTextView.setText(phone);



    }

//    @Override
//    public Filter getFilter() {
//
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//
//                String charString = charSequence.toString();
//
//                if (charString.isEmpty()) {
//
//                    listContacts = mArrayList;
//                } else {
//
//                    ArrayList<Contacts> filteredList = new ArrayList<>();
//
//                    for (Contacts contacts : mArrayList) {
//
//                        if (contacts.getName().toLowerCase().contains(charString)) {
//
//                            filteredList.add(contacts);
//                        }
//                    }
//
//                    listContacts = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = listContacts;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                listContacts = (ArrayList<Contacts>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }




    @Override
    public int getItemCount() {
        return listContacts.size();
    }


    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<Contacts> taskEntries) {
        listContacts = taskEntries;
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView, phoneTextView;



        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.contact_name);
            phoneTextView = (TextView)itemView.findViewById(R.id.ph_no);


        }
    }
}