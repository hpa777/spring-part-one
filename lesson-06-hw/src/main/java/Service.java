import ru.geekbrains.entity.Buyer;
import ru.geekbrains.entity.OrderDao;
import ru.geekbrains.entity.Product;

import java.util.List;

public class Service {

    private final OrderDao orderDao;

    public Service(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Buyer> getBuyersByProductId(Long pid) {
        return orderDao.listOfBuyersByProductId(pid);
    }

    public List<Product> getProductsByBuyerId(Long bid) {
        return orderDao.listOfPurchasedProductsByBuyerId(bid);
    }
}
