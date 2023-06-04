package dev.rivercat.fw_courier.module;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FoodInformation implements Parcelable {
    private String restaurantName;
    private String foodName;
    private Integer foodPrice;
    private String foodDescription;

    public FoodInformation(String restaurantName, String foodName, Integer foodPrice, String foodDescription) {
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDescription = foodDescription;
    }

    protected FoodInformation(Parcel in) {
        restaurantName = in.readString();
        foodName = in.readString();
        if (in.readByte() == 0) {
            foodPrice = null;
        } else {
            foodPrice = in.readInt();
        }
        foodDescription = in.readString();
    }

    public static final Creator<FoodInformation> CREATOR = new Creator<FoodInformation>() {
        @Override
        public FoodInformation createFromParcel(Parcel in) {
            return new FoodInformation(in);
        }

        @Override
        public FoodInformation[] newArray(int size) {
            return new FoodInformation[size];
        }
    };

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(restaurantName);
        parcel.writeString(foodName);
        if (foodPrice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(foodPrice);
        }
        parcel.writeString(foodDescription);
    }
}
