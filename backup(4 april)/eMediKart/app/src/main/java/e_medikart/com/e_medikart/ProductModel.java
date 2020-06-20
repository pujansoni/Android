package e_medikart.com.e_medikart;

/**
 * Created by lenovo on 07-03-2018.
 */

public class ProductModel {
    String productid;
    String productName;
    String productUse;
    String productPhoto;
    String productSideeffects;
    String productContent;
    String productPregsafety;
    String productPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUse() {
        return productUse;
    }

    public void setProductUse(String productUse) {
        this.productUse = productUse;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public String getProductSideeffects(){return productSideeffects;}

    public void setProductSideeffects(String productSideeffects){this.productSideeffects=productSideeffects;}

    public String getProductContent(){return productContent;}

    public void setProductContent(String productContent){this.productContent=productContent;}

    public String getProductPregsafety(){return productPregsafety;}

    public void setProductPregsafety(String productPregsafety){this.productPregsafety = productPregsafety;}

    public String getProductPrice(){return productPrice;}

    public void setProductPrice(String productPrice){this.productPrice = productPrice;}

    public String getProductId(){return productid;}

    public void setProductId(String productid){this.productid=productid;}
}
