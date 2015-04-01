package com.github.czyzby.kiwi.util.gdx.scene2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/** Contains common methods for Scene2D actors.
 *
 * @author MJ */
public class Actors {
	private Actors() {
	}

	/** Centers passed actor's position on its assigned stage according to their sizes. */
	public static void centerActor(final Actor actor) {
		centerActor(actor, actor.getStage());
	}

	/** Centers passed actor's position on the given stage according to their sizes. */
	public static void centerActor(final Actor actor, final Stage stage) {
		actor.setPosition((int) (stage.getWidth() / 2f - actor.getWidth() / 2f),
				(int) (stage.getHeight() / 2f - actor.getHeight() / 2f));
	}

	/** When called BEFORE resizing the stage, moves the actor to match the same aspect ratio as before. Useful
	 * for windows and dialogs in screen viewports.
	 *
	 * @param actor will be repositioned.
	 * @param newScreenSizeInStageCoords screen coords processed by stage. */
	public static void updateActorPosition(final Actor actor, final Vector2 newScreenSizeInStageCoords) {
		updateActorPosition(actor, actor.getStage(), newScreenSizeInStageCoords);
	}

	/** When called BEFORE resizing the stage, moves the actor to match the same aspect ratio as before. Useful
	 * for windows and dialogs in screen viewports.
	 *
	 * @param actor will be repositioned.
	 * @param stage has to be before resizing.
	 * @param newScreenSizeInStageCoords screen coords processed by stage. */
	public static void updateActorPosition(final Actor actor, final Stage stage,
			final Vector2 newScreenSizeInStageCoords) {
		actor.setPosition((int) ((actor.getX() + actor.getWidth() / 2f) / stage.getWidth()
				* newScreenSizeInStageCoords.x - actor.getWidth() / 2f),
				(int) ((actor.getY() + actor.getHeight() / 2f) / stage.getHeight()
						* newScreenSizeInStageCoords.y - actor.getHeight() / 2f));
	}

	/** Null-safe check if the actor has a stage. Especially useful for dialogs.
	 *
	 * @return true if actor is not null and has a stage. */
	public static boolean isShown(final Actor actor) {
		return actor != null && actor.getStage() != null;
	}
}