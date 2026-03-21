package com.guildaSys.exceptions;

public class MaximunItensInInvetoryException extends BusinessException {
    public MaximunItensInInvetoryException() {
        super("The maximum capacity of the inventory is 5 items.");
    }
}
