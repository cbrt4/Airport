package repository;

import entities.FlightEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import util.SessionFactoryUtil;

import java.util.List;

@Repository
public class FlightRepository implements RepositoryInterface<FlightEntity> {

    @Override
    public FlightEntity get(final int id) {
        return SessionFactoryUtil.transaction((Session session) ->
                session.get(FlightEntity.class, id));
    }

    @Override
    public void add(final FlightEntity flightEntity) {
        SessionFactoryUtil.transaction((Session session) ->
                session.save(flightEntity));
    }

    @Override
    public void remove(final int id) {
        SessionFactoryUtil.transaction((Session session) -> {
            FlightEntity flightEntity = get(id);
            session.delete(flightEntity);
            return flightEntity;
        });
    }

    @Override
    public void update(final FlightEntity flightEntity) {
        SessionFactoryUtil.transaction((Session session) -> {
            session.update(flightEntity);
            return flightEntity;
        });
    }

    @Override
    public List<FlightEntity> getAll() {
        return SessionFactoryUtil.transaction((Session session) ->
                session.createQuery("from FlightEntity ").list());
    }
}