package com.makingfitnessbetter.makingfitnessbetter.service;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.TransactionLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.vo.EntryExecTransactionLogVO;

public interface TransactionLogService {

    public TransactionLog createUserLog(User user);

    public TransactionLog createModifyExerciseTransactionLog(EntryExecTransactionLogVO transLog);


}
