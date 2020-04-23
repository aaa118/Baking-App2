package com.adi.baking.app2.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

    @SerializedName("quantity")
    @Expose
    @Nullable
    private Double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    public final static Creator<Ingredient> CREATOR = new Creator<Ingredient>() {

        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return (new Ingredient[size]);
        }

    };

    protected Ingredient(Parcel in) {
        this.quantity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.measure = ((String) in.readValue((String.class.getClassLoader())));
        this.ingredient = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Ingredient() {
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (quantity != null) dest.writeValue(quantity); else dest.writeValue(0);
        if (measure != null) dest.writeValue(measure); else dest.writeValue("");
        if (ingredient != null) dest.writeValue(ingredient); else dest.writeValue("");
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}