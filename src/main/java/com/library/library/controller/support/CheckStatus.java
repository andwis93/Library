package com.library.library.controller.support;

import com.library.library.domain.Copy;
import com.library.library.domain.support.Status;
import com.library.library.exceptions.RecordNotExistsException;
import com.library.library.service.CopyDbService;
import java.util.List;

public class CheckStatus {

    public boolean checkCopyStatus(List<Long> copyList, CopyDbService copyDbService)
            throws RecordNotExistsException {
        boolean allAvailable = true;
        for(Long copyId: copyList) {
            Copy copy = copyDbService.findById(copyId);
            if (copy.getStatus().equalsIgnoreCase(Status.AVAILABLE.getStatus())) {
                allAvailable = false;
                break;
            }
        }
        return allAvailable;
    }

}
