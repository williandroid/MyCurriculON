package br.auadeottoni.mycurriculon.modelo.dao;

import java.util.List;

public interface GenericDAO<T> {

    public void adiciona(T entidade);

    public void altera(T entidade);

    public void remove(Object iD);

    public T listaPorID(Object iD);

    public List<T> listaTudo();

}
