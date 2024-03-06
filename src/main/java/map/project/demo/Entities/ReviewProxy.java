package map.project.demo.Entities;

public class ReviewProxy implements ReviewComponent  {
    private final Review realReview;

    public ReviewProxy(Review realReview) {
        this.realReview = realReview;
    }

    @Override
    public String getStars() {
        if (verifyStars()) {
            return realReview.getStars();
        } else {
            return "Hidden review because of spam or hateful speech";
        }
    }

    @Override
    public String getComment() {
        if(verifyStars())
            return realReview.getComment();
        else
            return "Hidden review because of spam or hateful speech";
    }

    @Override
    public Boolean verifyStars() {
        int stars = Integer.parseInt(realReview.getStars().replaceAll("\\D+", ""));
        if (stars > 3)
            return true;
        return false;
    }

}
