package system.model.questions;

import java.io.Serializable;
import java.util.LinkedList;

public interface IAnswer extends Serializable{

    LinkedList<OneAnswer> getAnswers();
}
