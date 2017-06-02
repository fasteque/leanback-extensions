package com.fasteque.leanback.sample;

import java.util.ArrayList;
import java.util.List;

final class MovieList {
	static final String MOVIE_CATEGORY[] = {
			"Cards",
			"More cards",
			"Lots of cards",
			"Cards everywhere"
	};

	static List<Movie> list;

	static List<Movie> setupMovies() {
		list = new ArrayList<>();
		String title[] = {
				"Cards",
				"More cards",
				"Lots of cards",
				"Cards everywhere"
		};

		String description = "Fusce id nisi turpis. Praesent viverra bibendum semper. "
				+ "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est "
				+ "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit "
				+ "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit "
				+ "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id "
				+ "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat.";

		String videoUrl[] = {
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
		};
		String bgImageUrl[] = {
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
		};
		String cardImageUrl[] = {
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
				"http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
		};

		list.add(buildMovieInfo(MOVIE_CATEGORY[0], title[0], description, "Studio Zero", videoUrl[0], cardImageUrl[0],
				bgImageUrl[0]));
		list.add(buildMovieInfo(MOVIE_CATEGORY[1], title[1], description, "Studio One", videoUrl[1], cardImageUrl[1],
				bgImageUrl[1]));
		list.add(buildMovieInfo(MOVIE_CATEGORY[2], title[2], description, "Studio Two", videoUrl[2], cardImageUrl[2],
				bgImageUrl[2]));
		list.add(buildMovieInfo(MOVIE_CATEGORY[3], title[3], description, "Studio Three", videoUrl[3],
				cardImageUrl[3], bgImageUrl[3]));

		return list;
	}

	private static Movie buildMovieInfo(String category, String title, String description, String studio, String
			videoUrl, String cardImageUrl, String bgImageUrl) {
		Movie movie = new Movie();
		movie.setId(Movie.getCount());
		Movie.incCount();
		movie.setTitle(title);
		movie.setDescription(description);
		movie.setStudio(studio);
		movie.setCategory(category);
		movie.setCardImageUrl(cardImageUrl);
		movie.setBackgroundImageUrl(bgImageUrl);
		movie.setVideoUrl(videoUrl);
		return movie;
	}
}
