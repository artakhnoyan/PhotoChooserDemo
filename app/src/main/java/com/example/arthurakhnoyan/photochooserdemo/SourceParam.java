package com.example.arthurakhnoyan.photochooserdemo;

import android.content.Intent;

/**
 * Created by arthurakhnoyan on 8/2/16.
 */
public enum SourceParam {

	CLOSE("close"),
	OPEN("open"),
	RECENT("recent"),
	CAMERA("camera"),
	GALLERY("gallery"),
	DROPBOX("dropbox"),
	VK("vk"),
	FACEBOOK("facebook"),
	TWITTER("twitter"),
	INSTAGRAM("instagram"),
	FLICKR("flickr"),
	PICSART("picsart"),
	FREE_PICSART("free_picsart"),
	FREE_FLICKR("free_flickr"),
	FREE_GOOGLE("free_google"),
	USER_FLICKR("user_flickr"),
	USER_PICSART("user_picsart"),
	USER_GOOGLE("user_google"),
	OTHER("other"),
	TABBAR("tabbar"),
	SLIDE("slide"),

	EMAIL("email"),
	SMS("sms"),

	GRID("grid"),
	FRAME("frame"),
	BACKGROUND("background"),
	DRAFT("draft"),
	BLANK("blank"),
	PHOTO("photo"),
	EXIT_FROM_SHARE_SCREEN("exit_from_share_screen"),

	//editor_open
	//New FAB
	MM2_CAMERA_CAPTURE_DONE("mm2_camera_capture_done"),
	MM2_EDIT_SELECT_NEXT("mm2_edit_select_next"),
	MM2_EDIT_DOUBLE_TAP("mm2_edit_double_tap"),
	MM2_COLLAGE_GRID_DONE("mm2_collage_grid_done"),
	MM2_COLLAGE_FRAME_DONE("mm2_collage_frame_done"),
	MM2_COLLAGE_BACKGROUND_DONE("mm2_collage_background_done"),
	MM2_DRAW_BLANK_DONE("mm2_draw_blank_done"),
	MM2_DRAW_PHOTO_DONE("mm2_draw_photo_done"),
	MM2_DRAW_BACKGROUND_DONE("mm2_draw_background_done"),
	MM2_DRAW_DRAFT_DONE("mm2_draw_draft_done"),

	//Old FAB
	FAB_COLLAGE_FRAME_DONE("fab_collage_frame_done"),
	FAB_COLLAGE_GRID_DONE("fab_collage_grid_done"),
	FAB_COLLAGE_BACKGROUND_DONE("fab_collage_background_done"),
	FAB_DRAW_BACKGROUND_DONE("fab_draw_background_done"),
	FAB_DRAW_BLANK_DONE("fab_draw_blank_done"),
	FAB_DRAW_DRAFT_DONE("fab_draw_draft_done"),
	FAB_DRAW_PHOTO_DONE("fab_draw_photo_done"),
	FAB_EDIT_SELECT_NEXT("fab_edit_select_next"),
	FAB_RECENT_CLICK("fab_recent_click"),
	FAB_CAMERA_CAPTURE_DONE("fab_camera_capture_done"),

	//Studio Card
	SC_COLLAGE_BACKGROUND_DONE("sc_collage_background_done"),
	SC_COLLAGE_GRID_DONE("sc_collage_grid_done"),
	SC_COLLAGE_FRAME_DONE("sc_collage_frame_done"),
	SC_DRAW_BACKGROUND_DONE("sc_draw_background_done"),
	SC_DRAW_PHOTO_DONE("sc_draw_photo_done"),
	SC_DRAW_BLANK_DONE("sc_draw_blank_done"),
	SC_DRAW_DRAFT_DONE("sc_draw_draft_done"),
	SC_EDIT_SELECT_NEXT("sc_edit_select_next"),
	SC_EFFECT_DONE("sc_effect_done"),
	SC_CAMERA_DONE("sc_camera_done"),

	FTE_PROFILE_FTE_CARD_EDIT("fte_profile_fte_card_edit"),
	FTE_NETWORK_FTE_CARD_EDIT("fte_network_fte_card_edit"),
	FTE_PHOTO_EDIT("fte_photo_edit"),
	NOTIFICATION("notification"),
	NOTIFICATIONS("notifications"),
	FILE_MANAGER("file_manager"),
	FIND_FRIENDS("find_friends"),
	FIND_ARTISTS("find_artists"),
	CHECK("check"),
	UNCHECK("uncheck"),


	FTE_EDITIONS("fte_editions"),

	//MyNetwork
	MY_NETWORK("my_network"),

	//Similar Photos
	SIMILAR_PHOTOS("similar_photos"),

	//Photo_Stream
	PHOTO_STREAM("photo_stream"),

	BROWSER("browser"),
	PROFILE("profile"),

	///Drawing
	SOURCE_EDITOR("editor"),

	REGISTRATION("registration"),
	DRAWER("drawer"),
	CONTACTS("contacts"),

	//Search
	SEARCH("search"),
	RESULT_CLICK("result_click"),
	ICON_CLICK("icon_click"),
	THEME("theme"),
	PACKAGE("package"),
	CANCEL("cancel"),
	SHOP("shop"),
	EXPLORE("explore"),
	ARTISTS("artists"),
	USER("user"),
	ALL("all"),
	TAGS("tags"),
	TAG("tag"),
	PAID("paid"),
	IMAGES("images"),
	IMAGE("image"),
	PHOTOS("photos"),
	FREE("free"),
	FEATURED("featured"),
	RECENT_SEARCH("recent_search"),

	//NOTIFICATIONS
	USER_TAG_ADDED("user_tag_added"),
	FRIEND_JOINED("friend_joined"),
	REPOST("repost"),
	MENTION("mention"),
	PUBLISH("publish"),
	FOLLOW("follow"),
	COMMENT("comment"),
	LIKE("like"),
	FTE_USED("fte_used"),
	ITEM_CLICK("item_click"),
	POST_IMAGE("post_image"),
	CREATE_ACCOUNT("create_account"),

	//Appboy params values
	/*
	Send in case source = 'editor" or "collage". When clicking on Save icon the value should be "save_button".
    When clicking on Done icon the value should be "done_button".
     When clicking on close dialog options in case of Android, send " close_dialog".
     Photo_upload event should be fired in case of all the options.
     */
	SAVE_BUTTON("save_button"),
	DONE_BUTTON("done_button"),
	CLOSE_BUTTON("done_close"),
	CLOSE_DIALOG("close_dialog"),
	LOCAL("local"),

	//OnBoarding
	SAVE("save"),
	DONE("done"),
	CREATE_PIECE("create_piece"),
	SIGN_UP("sign_up"),
	LOGIN("login"),
	FAIL("fail"),
	ONBOARDING("onboarding"),
	//SHOP
	UNKNOWN("unknown"),
	FROM("from"),
	CATEGORY("category"),
	SHOP_SEARCH("shop_search"),
	PACKAGE_PREVIEW("package_preview"),
	BANNER("banner"),
	EDITOR_ADD_CLIPART("editor_add_clipart"),
	DRAWING_ADD_CLIPART("drawing_add_clipart"),
	DRAWING_ADD_TEXT("drawing_add_text"),
	CLIPART("clipart"),
	TEXT("text"),
	TEXTART("textart"),
	COLLAGE_FRAME("collage_frame"),
	EDITOR_ADD_TEXT("editor_add_text"),
	DOWNLOAD("download"),
	PURCHASE("purchase"),
	UNINSTALL("uninstall"),
	USE("use"),
	LIST("list"),
	BUNDLE("bundle"),
	PREVIEW("preview"),
	THEMES("themes"),
	INSTALLED("installed"),
	SWIPE("swipe"),
	CLICK("click"),
	SIMILAR_PACKAGES("similar_packages");


	private String name;

	SourceParam(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static SourceParam getValue(String type) {
		return type == null ? null : SourceParam.valueOf(type.toUpperCase());
	}

	// *****************************
	// these is for avoiding to serialize SourceParam when passing through intent
	private static final String KEY_SOURCE_PARAM = "source_param_key";

	public void attachTo(Intent intent) {
		intent.putExtra(KEY_SOURCE_PARAM, ordinal());
	}

	public static SourceParam detachFrom(Intent intent) {
		if (!intent.hasExtra(KEY_SOURCE_PARAM)) {
			return null;
		}
		return values()[intent.getIntExtra(KEY_SOURCE_PARAM, -1)];
	}

	public static void removeFrom(Intent intent) {
		intent.removeExtra(KEY_SOURCE_PARAM);
	}
	// *****************************
}

