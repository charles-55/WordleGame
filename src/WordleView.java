public interface WordleView {

    void handleInvalidPlay();
    void handlePlayUpdate(WordlePlayEvent event);
    void handleStatusUpdate(WordleModel.Status status);
}
