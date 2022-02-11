package com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailViewModel extends ViewModel {

    private final ProductRepo productRepo;

    public ProductDetailViewModel(Application mApplication) {
        productRepo = ProductRepo.getInstance(mApplication.getApplicationContext());
    }

    public void populateDB() {
        List<ProductInfo> products = new ArrayList<>();
        products.add(new ProductInfo("Robot Vacuum Cleaner",
                "The Robot Vacuum Cleaner! The automatic vacuum cleaners that we see moving around the house today use a variety of technology such as remote access, mapping of your home and so on.",
                2499,
                28.613939, 77.209023));

        products.add(new ProductInfo("Indoor Security Camera",
                "Thanks to the smart indoor security camera now you can easily keep a watchful eye on your home. The best part about these smart cameras is that they are not just electronic eyes but also come with a range of unique features.",
                2997,
                39.299892, -75.618935));

        products.add(new ProductInfo("Portable Chargers And Power Bank",
                "Between the never-ending to-do list, watching your phone run out of battery when there’s no place to plug in is no fun and game. Mobile devices like tablets and mobile phones make life so much more convenient until they run out of battery power.",
                1717,
                37.630322, -104.790543));

        products.add(new ProductInfo("Dishwasher",
                "After a long tiring day at work and with so many other things demanding your attention all the time in today’s hectic lifestyle, wouldn’t a dishwasher make your lives much easier? Gone are the days when dishwashers were treated as a luxury item as every other household today has one of these.",
                45099,
                28.613939, 77.209023));

        products.add(new ProductInfo("Smart Alarm Clocks",
                "Nothing is worse than waking up to an insane alarm clock that is louder than Jackhammer. For making your mornings more pleasant and beautiful, choose a smart alarm clock that comes with multiple unique features and most importantly gently wakes you up with natural light.",
                1250,
                28.613939, 77.209023));

        products.add(new ProductInfo("Electric Deep Fryer",
                "Do you plan to be on a healthy diet but due to some or the other reason are not able to keep at it? Well, mostly it’s because it is hard to resist delicious deep-fried dishes. Who could say no to deep-fried pakoras or samosas for that matter?",
                5999,
                28.613939, 77.209023));

        products.add(new ProductInfo("Coffee Maker Machine",
                "It is the perfect appliance for those who can’t begin their day without a cup of coffee. This appliance makes your life easier when you are rushing against your deadline in the morning. With life getting busier each day, a machine that prepares coffee quickly without compromising on the taste is surely heaven for coffee addicts.",
                2900,
                28.613939, 77.209023));

        products.add(new ProductInfo("Laptop Cooling Pad",
                "If you have been using your laptop for a long time, you might have noticed instances of rebooting. This is very likely to be a case of laptop heating up. Overheating of laptop not just harms the device or leads to hardware failure but also causes problem to the area on which your laptop is placed.",
                672,
                28.613939, 77.209023));

        products.add(new ProductInfo("Bullet Mixer",
                "Mixer is known to immensely save your time and helps you prevent the clutter which you would otherwise get by manually chopping. Either you want to prepare a quick smoothie or soup, the bullet mixer grinder saves a great deal of time and stress and makes your life much easier in the kitchen.",
                6688,
                28.613939, 77.209023));

        products.add(new ProductInfo("Hair dryer and straightener",
                "Owning a hair dryer and straightener has become highly common these days. With the ever-changing fashion, we are greatly reliable on appliances. The investment saves us from the heavy cost that goes behind getting our hair styled at the salon.",
                1899,
                28.613939, 77.209023));
        productRepo.populateDB(products);
    }

    public SingleLiveEvent<List<ProductInfo>> getProductsFromRepo() {
        return productRepo.getProductsInfo();
    }

    public void getAllProductsFromRepo() {
        productRepo.getAllProductsFromRepo();
    }
}
