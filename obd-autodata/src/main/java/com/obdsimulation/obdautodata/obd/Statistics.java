package com.obdsimulation.obdautodata.obd;

public class Statistics {

    private int vehicles;
    private int users;
    private int admins;
    private int errors;
    private String repairs;
    private String costs;

    /**
     * @return the vehicles
     */
    public int getVehicles() {
        return vehicles;
    }

    /**
     * @return the costs
     */
    public String getCosts() {
        return costs;
    }

    /**
     * @param costs the costs to set
     */
    public Statistics setCosts(String costs) {
        this.costs = costs;
        return this;
    }

    /**
     * @return the repairs
     */
    public String getRepairs() {
        return repairs;
    }

    /**
     * @param repairs the repairs to set
     */
    public Statistics setRepairs(String repairs) {
        this.repairs = repairs;
        return this;
    }

    /**
     * @return the errors
     */
    public int getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public Statistics setErrors(int errors) {
        this.errors = errors;
        return this;
    }

    /**
     * @return the admins
     */
    public int getAdmins() {
        return admins;
    }

    /**
     * @param admins the admins to set
     */
    public Statistics setAdmins(int admins) {
        this.admins = admins;
        return this;
    }

    /**
     * @return the users
     */
    public int getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public Statistics setUsers(int users) {
        this.users = users;
        return this;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public Statistics setVehicles(int vehicles) {
        this.vehicles = vehicles;
        return this;
    }
}
