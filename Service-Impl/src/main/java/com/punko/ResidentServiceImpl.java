package com.punko;

import com.punko.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    ResidentDAO residentDAO;

    @Override
    public List<Resident> getAllResident() {
        return residentDAO.getAllResident();
    }
}
