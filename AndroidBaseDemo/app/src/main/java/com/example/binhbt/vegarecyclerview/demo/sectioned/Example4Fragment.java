package com.example.binhbt.vegarecyclerview.demo.sectioned;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhbt.myapplication.R;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.vn.vega.adapter.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.vn.vega.adapter.sectionedrecyclerviewadapter.StatelessSection;
import com.vn.vega.widget.RecyclerViewWrapper;

import java.util.ArrayList;
import java.util.List;


public class Example4Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    SectionedRecyclerViewAdapter sectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex4, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();


        for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
            List<String> contacts = getContactsWithLetter(alphabet);

            if (contacts.size() > 0) {
                sectionAdapter.addSection(new ExpandableContactsSection(String.valueOf(alphabet), contacts));
            }
        }

        RecyclerViewWrapper recyclerView = (RecyclerViewWrapper) view.findViewById(R.id.list);
        recyclerView.setAdapter(sectionAdapter);
        recyclerView.setRefreshListener(this);
        recyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        recyclerView.setupMoreListener(this, 1);
        return view;
    }
    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_LONG).show();
        sectionAdapter.notifyDataSetChanged();
    }
    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        Toast.makeText(getActivity(), "More", Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
                    List<String> contacts = getContactsWithLetter(alphabet);

                    if (contacts.size() > 0) {
                        sectionAdapter.addSection(new ExpandableContactsSection(String.valueOf(alphabet), contacts));
                    }
                }
                sectionAdapter.notifyDataSetChanged();
            }
        }, 3000);

    }
    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(R.string.nav_example4);
        }
    }

    private List<String> getContactsWithLetter(char letter) {
        List<String> contacts = new ArrayList<>();

        for (String contact : getResources().getStringArray(R.array.names)) {
            if (contact.charAt(0) == letter) {
                contacts.add(contact);
            }
        }

        return contacts;
    }

    class ExpandableContactsSection extends StatelessSection {

        String title;
        List<String> list;
        boolean expanded = true;

        public ExpandableContactsSection(String title, List<String> list) {
            super(R.layout.section_ex4_header, R.layout.section_ex4_item);

            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return expanded? list.size() : 0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name = list.get(position);

            itemHolder.tvItem.setText(name);
            itemHolder.imgItem.setImageResource(name.hashCode() % 2 == 0 ? R.drawable.ic_face_black_48dp : R.drawable.ic_tag_faces_black_48dp);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    headerHolder.imgArrow.setImageResource(
                            expanded ? R.drawable.ic_keyboard_arrow_up_black_18dp : R.drawable.ic_keyboard_arrow_down_black_18dp
                    );
                    sectionAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvTitle;
        private final ImageView imgArrow;

        public HeaderViewHolder(View view) {
            super(view);

            rootView = view;
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            imgArrow = (ImageView) view.findViewById(R.id.imgArrow);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvItem;

        public ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
        }
    }
}
