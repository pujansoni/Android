package e_medikart.com.e_medikart;

/**
 * Created by lenovo on 06-03-2018.
 */

public class CategoriesModel  {
    String cat_id;
    String manufacturer_id;
    String manufacturer_name;
    String manufacturer_description;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(String manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getManufacturer_description(){return manufacturer_description;}

    public void setManufacturer_description(String manufacturer_description)
    {
        this.manufacturer_description = manufacturer_description;
    }
}
