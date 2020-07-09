package edu.miu.cs.cs525.reversi.common ;

public abstract class GeneralPlayer
{
    public abstract Location getMove( BoardInfo b ) ;

    public abstract String identify() throws Exception ;
}
