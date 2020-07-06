package edu.miu.cs.cs525.reversi.view;

public class UIAdapter implements UITarget{

    private UIAdaptee uiAdaptee = new UIAdaptee();

    @Override
    public void showBorderView() throws Exception{
        uiAdaptee.showBorderView();
    }

    @Override
    public void showNtkUIView() throws Exception{
        uiAdaptee.showNtkUIView();
    }

    @Override
    public void showPlayersUIView() throws Exception{
        uiAdaptee.showPlayersUIView();
    }
}
