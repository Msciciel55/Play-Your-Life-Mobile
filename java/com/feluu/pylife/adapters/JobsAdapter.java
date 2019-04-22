package com.feluu.pylife.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feluu.pylife.R;
import com.feluu.pylife.models.ViewModel;

import java.util.List;

public class JobsAdapter extends PagerAdapter {

    private List<ViewModel> models;
    private Context context;

    public JobsAdapter(List<ViewModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView;
        TextView title, description, earnings, locations;
        LayoutInflater layoutInflater;

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.casual_item, container, false);

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        earnings = view.findViewById(R.id.earnings);
        locations = view.findViewById(R.id.locations);
        description = view.findViewById(R.id.description);

        Glide
                .with(context)
                .load(context.getResources().getDrawable(models.get(position).getImage()))
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(imageView);
        title.setText(models.get(position).getTitle());
        earnings.setText(models.get(position).getEarnings());
        locations.setText(models.get(position).getLocations());
        description.setText(models.get(position).getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}