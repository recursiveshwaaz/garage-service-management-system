import java.util.*;

public class GarageService {
     private Map<String,Customer> customersMap ;
     private List<Service> availableService ;

    public GarageService() {
        this.customersMap = new HashMap<>();
        this.availableService = new ArrayList<>();
        loadService();
    }

    //databases

    public void loadService(){
        availableService.add(new Service("Car wash",500));
        availableService.add(new Service("Oil Change",700));
        availableService.add(new Service("Wheel Alignment",300));
        availableService.add(new Service("Tire Replacement",3000));
        availableService.add(new Service("Puncture",50));
    }


    public void addCustomer(String name,String phone,String carNumber,String model){
        Car car = new Car(carNumber,model) ;
        Customer customer = new Customer(name,phone,car) ;
        customersMap.put(carNumber,customer);
        System.out.println("Customer added successfully!");
    }

    public void createInvoice(String carNumber){
        if(!customersMap.containsKey(carNumber)){
            System.out.println("No Customer Found with car number: "+carNumber);
            return;
        }

        Scanner sc = new Scanner(System.in) ;
        Customer customer = customersMap.get(carNumber) ;
        Invoice invoice = new Invoice(customer) ;

        System.out.println("Aailable Services: ");
        for(int i = 0 ;i<availableService.size();i++){
            System.out.println((i+1)+". "+availableService.get(i).getName()
                    +" - "+availableService.get(i).getPrice());
        }
        while (true){
            System.out.println("Enter Service Number to add 0 to finish: ");
            int choice = sc.nextInt() ;

            if(choice == 0) break;
            if(choice > 0 && choice<= availableService.size()){
                invoice.addService(availableService.get(choice-1));
                System.out.println("Service Done!");
            }
            else{
                System.out.println("Service not available !");
            }

        }
        invoice.printInvoice();
    }
}
