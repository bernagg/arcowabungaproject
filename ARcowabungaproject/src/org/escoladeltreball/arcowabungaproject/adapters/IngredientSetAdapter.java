/*
 *  IngredientSetAdapter.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *  Marc Sabate Piñol <masapim@hotmail.com>
 *  Victor Purcallas Marchesi <vpurcallas@gmail.com>
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
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ToggleButton;

public class IngredientSetAdapter extends BaseAdapter {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    public LayoutInflater inflater;
    public Activity activity;

    // ====================
    // CONSTRUCTORS
    // ====================

    public IngredientSetAdapter(Activity activity, Set<Ingredient> ingredients) {
	this.activity = activity;
	for (Ingredient ingredient : ingredients) {
	    this.ingredients.add(ingredient);
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
    // ================ ====

    @Override
    public int getCount() {
	return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
	return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
	return ingredients.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder = null;
	if (convertView == null) {
	    holder = new ViewHolder();
	    convertView = inflater.inflate(R.layout.list_item_ingredient, null);
	    holder.tb = (ToggleButton) convertView
		    .findViewById(R.id.toggle_button_ingredient_item);
	    CustomTextView.customTextView(activity, holder.tb);
	    holder.tb.setTextColor(Color.BLACK);
	    ;
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}
	Ingredient ingredient = (Ingredient) getItem(position);
	holder.tb.setTextOn(ingredient.getName());
	holder.tb.setTextOff(ingredient.getName());
	holder.tb.setText(ingredient.getName());
	return convertView;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    static class ViewHolder {
	public ToggleButton tb;
    }
}
