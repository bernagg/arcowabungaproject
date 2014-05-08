/*
 *  PizzaSetAdapter.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Copyright 2014 	Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  			Marc Sabate Piñol <masapim@hotmail.com>
 *  			Victor Purcallas Marchesi <vpurcallas@gmail.com>
 *  			Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *
 *   ARcowabungaproject is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   ARcowabungaproject is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with ARcowabungaproject.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.dao.DAOAndroid;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PizzaSetAdapter extends BaseExpandableListAdapter {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private final List<Pizza> pizzas = new ArrayList<Pizza>();
    public LayoutInflater inflater;
    public Activity activity;

    // ====================
    // CONSTRUCTORS
    // ====================

    public PizzaSetAdapter(Activity activity, Set<Pizza> pizzas) {
	this.activity = activity;
	for (Pizza pizza : pizzas) {
	    this.pizzas.add(pizza);
	}
	inflater = activity.getLayoutInflater();
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public Object getChild(int groupPosition, int childPosition) {
	return pizzas.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {
	final Pizza children = (Pizza) getChild(groupPosition, childPosition);
	ChildViewHolder holder = null;
	if (convertView == null) {
	    holder = new ChildViewHolder();
	    convertView = inflater
		    .inflate(R.layout.expanded_pizza_layout, null);
	    holder.tvDesc = (TextView) convertView
		    .findViewById(R.id.textInSubItem);
	    holder.ivIcon = (ImageView) convertView
		    .findViewById(R.id.imageInSubItem);
	    holder.llButton = (LinearLayout) convertView
		    .findViewById(R.id.pizzaButtonInSubItem);
	    CustomTextView.customTextView(activity, holder.tvDesc);
	    CustomTextView.customTextView(activity, (TextView) convertView
		    .findViewById(R.id.textButtonInPizzaSubItem));
	    convertView.setTag(holder);
	} else {
	    holder = (ChildViewHolder) convertView.getTag();
	}

	holder.tvDesc.setText(children.getIngedientsDescription());

	DAOAndroid dao = DAOAndroid.getInstance();
	Drawable icon = dao.getDrawableFromAssets(activity, children.getIcon());
	holder.ivIcon.setBackgroundDrawable(icon);

	holder.llButton.setOnClickListener(new ARButtonClickListener(
		groupPosition));

	return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
	return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
	return pizzas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
	return pizzas.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
	return pizzas.get(groupPosition).getId();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
	    View convertView, ViewGroup parent) {
	Pizza group = (Pizza) getGroup(groupPosition);

	GroupViewHolder holder = null;
	if (convertView == null) {
	    holder = new GroupViewHolder();
	    convertView = inflater
		    .inflate(R.layout.listitem_pizza_layout, null);
	    holder.ivIcon = (ImageView) convertView
		    .findViewById(R.id.imageInItem);
	    holder.tvTitle = (TextView) convertView
		    .findViewById(R.id.titleTextInItem);
	    holder.tvPrice = (TextView) convertView
		    .findViewById(R.id.priceTextInItem);
	    holder.tvDesc = (TextView) convertView
		    .findViewById(R.id.descTextInItem);
	    holder.ibAdd = (ImageButton) convertView
		    .findViewById(R.id.imageButtonInItem);
	    CustomTextView.customTextView(activity, holder.tvTitle);
	    CustomTextView.customTextView(activity, holder.tvPrice);
	    CustomTextView.customTextView(activity, holder.tvDesc);
	    convertView.setTag(holder);
	} else {
	    holder = (GroupViewHolder) convertView.getTag();
	}
	DAOAndroid dao = DAOAndroid.getInstance();
	Drawable icon = dao.getDrawableFromAssets(activity, group.getIcon());
	holder.ivIcon.setBackgroundDrawable(icon);

	holder.tvTitle.setText(group.getName());
	holder.tvPrice.setText(group.getFormatedPrice());
	String desc = group.getIngedientsDescription();
	if (desc.length() > 20) {
	    desc = desc.substring(0, 17) + "...";
	}
	String showMore = "<font color='#FF0000'>"
		+ activity.getResources().getString(R.string.show_more)
		+ "</font>";
	holder.tvDesc.setText(Html.fromHtml(desc + showMore));

	holder.ibAdd.setOnClickListener(new AddButtonClickListener(group));

	return convertView;
    }

    @Override
    public boolean hasStableIds() {
	return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
	return false;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public class ARButtonClickListener implements OnClickListener {

	private int index;

	public ARButtonClickListener(int index) {
	    this.index = index;
	}

	@Override
	public void onClick(View v) {
	    // Intent i = new Intent(activity, ARViewActivity.class);
	    // Pasarle pizza
	    // i.putExtra("pizza", pizzas.get(index).getId);
	    // activity.startActivity(i);
	    pizzas.get(index);
	}
    }

    public class AddButtonClickListener implements OnClickListener {

	private Product product;

	public AddButtonClickListener(Product product) {
	    this.product = product;
	}

	@Override
	public void onClick(View v) {
	    Pizzeria.getInstance().getShoppingCart().addProduct(product);
	    Toast.makeText(activity,
		    "S'ha afegit una producte al carro de la compra",
		    Toast.LENGTH_SHORT).show();
	}

    }

    public static class ChildViewHolder {
	public TextView tvDesc;
	public ImageView ivIcon;
	public LinearLayout llButton;
    }

    public static class GroupViewHolder {
	public ImageView ivIcon;
	public TextView tvTitle;
	public TextView tvPrice;
	public TextView tvDesc;
	public ImageButton ibAdd;
    }
}
