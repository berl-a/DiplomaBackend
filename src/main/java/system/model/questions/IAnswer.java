package system.model.questions;

import system.dao.Idable;

import java.io.Serializable;
import java.util.LinkedList;

public interface IAnswer extends Serializable{

    LinkedList<OneAnswer> getAnswers();
}
