package com.adi.baking.app2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeName implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("servings")
    @Expose
    private Double servings;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Creator<RecipeName> CREATOR = new Creator<RecipeName>() {

        @SuppressWarnings({
                "unchecked"
        })
        public RecipeName createFromParcel(Parcel in) {
            return new RecipeName(in);
        }

        public RecipeName[] newArray(int size) {
            return (new RecipeName[size]);
        }

    };

    protected RecipeName(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.ingredients, (Ingredient.class.getClassLoader()));
        in.readList(this.steps, (Step.class.getClassLoader()));
        this.servings = ((Double) in.readValue((Double.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RecipeName() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Double getServings() {
        return servings;
    }

    public void setServings(Double servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "RecipeName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }
}