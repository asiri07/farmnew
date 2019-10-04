package com.msd.fixAssetRegister.service;

import com.msd.fixAssetRegister.model.AJobDefine;
import com.msd.fixAssetRegister.repository.AJobDefineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AJobDefineService  {

    @Autowired
    AJobDefineRepository aJobDefineRepository;


    @Transactional
    public void deleteJobs(int ref_user_type) {
        this.aJobDefineRepository.deleteJobs(ref_user_type);
    }

    @Transactional
    public void deleteJobsbyBranch(int branch) {
        this.aJobDefineRepository.deleteJobsbyBranch(branch);
    }

    @Transactional
    public void saveAll(List<AJobDefine> aJobDefine) {
        aJobDefineRepository.saveAll(aJobDefine);
    }
}
