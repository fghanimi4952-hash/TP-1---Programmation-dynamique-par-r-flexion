package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("IDao dependency has not been injected");
        }
        return dao.getValue() * 2;
    }
}
