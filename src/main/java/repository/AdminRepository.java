package repository;

import entities.AdminEntity;
import entities.FlightEntity;
import org.hibernate.Session;
import util.SessionFactoryUtil;

import java.util.List;

public class AdminRepository implements RepositoryInterface<AdminEntity> {
    @Override
    public AdminEntity get(final int id) {
        return SessionFactoryUtil.transaction((Session session) ->
                session.get(AdminEntity.class, id));
    }

    @Override
    public void add(final AdminEntity adminEntity) {
        SessionFactoryUtil.transaction((Session session) ->
                session.save(adminEntity));
    }

    @Override
    public void remove(int id) {
        SessionFactoryUtil.transaction((Session session) -> {
            AdminEntity adminEntity = get(id);
            session.delete(adminEntity);
            return adminEntity;
        });
    }

    @Override
    public void update(final AdminEntity adminEntity) {
        SessionFactoryUtil.transaction((Session session) -> {
            session.update(adminEntity);
            return adminEntity;
        });
    }

    @Override
    public List<AdminEntity> getAll() {
        return SessionFactoryUtil.transaction((Session session) ->
                session.createQuery("from AdminEntity").list());
    }
}
