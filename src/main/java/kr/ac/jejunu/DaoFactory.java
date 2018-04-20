package kr.ac.jejunu;

public class DaoFactory {
    public ProductDao getProductDao() {
        return new ProductDao(getConnetionMaker());
    }

    public ConnectionMaker getConnetionMaker() {
        return new JejuConnectionMaker();
    }
}
