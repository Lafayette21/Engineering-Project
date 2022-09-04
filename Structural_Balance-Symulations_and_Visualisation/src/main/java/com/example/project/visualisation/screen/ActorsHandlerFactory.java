package com.example.project.visualisation.screen;

import com.example.project.visualisation.actorshandler.*;

public class ActorsHandlerFactory {
    private ActorsHandlerFactory() {
        throw new RuntimeException("Class ActorsHandlerFactory cannot be instantiated");
    }

    static ActorHandler prepareActorsHandler(int actorsNumber) {
        if (actorsNumber <= 10) {
            return new TenActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 20) {
            return new TwentyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 30) {
            return new ThirtyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 40) {
            return new FortyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 50) {
            return new FiftyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 60) {
            return new SixtyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 70) {
            return new SeventyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 80) {
            return new EightyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 90) {
            return new NinetyActorsHandler(actorsNumber);
        }
        return new HundredActorsHandler(actorsNumber);
    }
}
