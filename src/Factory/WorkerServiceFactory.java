package Factory;

import Service.IWorkerService;
import Service.Impl.PatientServiceImpl;
import Service.Impl.ManagerServiceImpl;
import Service.Impl.DoctorServiceImpl;

public class WorkerServiceFactory {
    public static IWorkerService getService(int workerType){
        IWorkerService service = null;
        switch (workerType){
            case 0:
                service = new DoctorServiceImpl(DataAccessFactory.getRepository("txt","doctors.txt"));
                break;
            case 1:
                service = new ManagerServiceImpl(DataAccessFactory.getRepository("txt","managers.txt"));
                break;
            case 2:
                service = new PatientServiceImpl(DataAccessFactory.getRepository("txt","patients.txt"));
        }
        return service;
    }
}
