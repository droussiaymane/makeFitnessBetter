package com.makingfitnessbetter.makingfitnessbetter.service;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.vo.CreateEntryLogVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitEntryLog;

import java.util.List;

public interface EntryLogService{

    //CREATE ENTRYLOG
    EntryLog createEntry(CreateEntryLogVO entryLogVo, Integer id);

    List<EntryLog> fetchAllEntryRecords(Integer id);

    public EntryLog submitEntryLog(SubmitEntryLog submitEntryLog);
}
