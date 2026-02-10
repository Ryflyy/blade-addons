package blade.addon.features.dungeon;

import blade.addon.utils.Location;
import blade.addon.utils.config.values.Dungeons;
import blade.addon.utils.events.Events;
import blade.addon.utils.rendering.RenderUtils;
import config.practical.hud.HUDComponent;
import net.minecraft.client.gui.DrawContext;

public class QuizTimer {

    private static final int START_DURATION = 220;
    private static final int QUESTION_DURATION = 100;
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;

    private static int tick = 0;
    private static int progress = 0;

    public static void init() {

        Events.ON_GAME_MESSAGE.register(text -> {
            if (!Location.inDungeon()) return false;

            String string = text.getString();
            if (string.equals("[STATUE] Oruo the Omniscient: I am Oruo the Omniscient. I have lived many lives. I have learned all there is to know.")) {
                tick = START_DURATION;
                progress = FIRST;
            } else if (string.equals("[STATUE] Oruo the Omniscient: 2 questions left... Then you will have proven your worth to me!")) {
                tick = QUESTION_DURATION;
                progress = SECOND;
            } else if (string.equals("[STATUE] Oruo the Omniscient: One more question!")) {
                tick = QUESTION_DURATION;
                progress = THIRD;
            }

            return false;
        });

        Events.ON_SERVER_TICK.register(() -> {
            tick = Math.max(tick - 1, 0);
            return false;
        });

        Events.ON_LOCATION_CHANGE.register(newLocation -> {
            tick = 0;
            progress = 0;
            return false;
        });
    }

    public static boolean display() {
        return Dungeons.quizTimer && tick > 0;
    }

    public static void render(HUDComponent component, DrawContext context) {
        RenderUtils.drawPrefixedTimer(component, context, both(), tick);
    }
    private static String both() {
        if (Dungeons.quizProgress) {
            return "Quiz (" + progress + "/3)";
        }
        return "Quiz";
    }
    }
