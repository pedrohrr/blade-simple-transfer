package com.pedrohrr.simpletransfer.service;

import com.blade.ioc.annotation.Bean;
import com.pedrohrr.simpletransfer.enumeration.TransferStatus;
import com.pedrohrr.simpletransfer.exception.NotFoundException;
import com.pedrohrr.simpletransfer.model.Transfer;

import java.util.List;

import static com.pedrohrr.simpletransfer.predicate.ModelPredicate.*;

@Bean
public class TransferService extends AbstractService<Transfer> {

    private static final String SENDER_CLIENT_QUERY = "select t.id from transfers t join accounts a on t.sender = a.id where a.client = ?";
    private static final String RECEIVER_CLIENT_QUERY = "select t.id from transfers t join accounts a on t.receiver = a.id where a.client = ? and t.status = ?";

    public List<Transfer> findBySenderId(final Long id) throws NotFoundException {
        return find(getSenderIdPredicate(id));
    }

    public List<Transfer> findByReceiverId(final Long id) throws NotFoundException {
        return find(getReceiverIdPredicate(id));
    }

    public List<Transfer> findBySenderClientId(final Long id) throws NotFoundException {
        return bySQL(SENDER_CLIENT_QUERY, id);
    }

    public List<Transfer> findByReceiverClientId(final Long id) throws NotFoundException {
        return bySQL(RECEIVER_CLIENT_QUERY, id, TransferStatus.COMPLETED);
    }

    public List<Transfer> findByStatus(final TransferStatus status) throws NotFoundException {
        return find(getStatusPredicate(status));
    }

    @Override
    Class<Transfer> modelClass() {
        return Transfer.class;
    }

}
