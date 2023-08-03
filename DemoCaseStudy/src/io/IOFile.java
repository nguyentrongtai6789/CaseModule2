package io;

import java.util.List;

public interface IOFile <E>{
    void write(List<E> eList);
    void read();
}
