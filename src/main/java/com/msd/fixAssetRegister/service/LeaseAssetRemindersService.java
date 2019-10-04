package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.LeaseAssetReminders;
import com.msd.fixAssetRegister.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaseAssetRemindersService {



    @Autowired
    LeaseAssetRemindersRepository leaseAssetRemindersRepository;


    public  LeaseAssetReminders saveUpdateLeaseAssetReminders(LeaseAssetReminders leaseAssetReminders) {
        return leaseAssetRemindersRepository.save(leaseAssetReminders);
    }


    @Transactional
    public void deleteAll() {
       leaseAssetRemindersRepository.deleteAll();
    }

}
