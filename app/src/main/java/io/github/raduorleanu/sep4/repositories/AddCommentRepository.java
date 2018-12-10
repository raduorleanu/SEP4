package io.github.raduorleanu.sep4.repositories;

import io.github.raduorleanu.sep4.adapters.CommentListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.AddCommentDbHandler;

public class AddCommentRepository {

    private CommentListAdapter commentListAdapter;
    private AddCommentDbHandler dbHandler;

    private static AddCommentRepository repository;

    private AddCommentRepository() {
        dbHandler = new AddCommentDbHandler();
    }

    public static AddCommentRepository getRepository() {
        if (repository == null) {
            repository = new AddCommentRepository();
            return repository;
        }
        return repository;
    }
}
