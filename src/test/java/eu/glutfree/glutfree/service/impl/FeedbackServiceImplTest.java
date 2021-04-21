package eu.glutfree.glutfree.service.impl;


import eu.glutfree.glutfree.model.entities.FeedbackEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import eu.glutfree.glutfree.model.service.FeedbackAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.repository.FeedbackRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import eu.glutfree.glutfree.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceImplTest {

        private FeedbackEntity feedbackEntity1, feedbackEntity2;
        private UserEntity testUser1, testUser2;
        private FeedbackAddServiceModel feedbackAddServiceModel;

        private final static Long FEEDBACK_ID = 2L;



        private UserService mockuserService;
        private FeedbackServiceImpl serviceToTest;

        @Mock
        FeedbackRepository mockfeedbackRepository;
        @Mock
        ModelMapper mockModelMapper;
        @Mock
        CloudinaryService cloudinaryService;

        @BeforeEach
        public void init() {

            mockuserService = Mockito.mock(UserService.class);

            feedbackEntity1 = new FeedbackEntity();

            feedbackEntity1.setFeedbackText("Тестов текст");
            feedbackEntity1.setName("neme1");
            feedbackEntity1.setUrlToPic(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity1.getName() ));
            feedbackEntity1.setScore(5);
            feedbackEntity1.setWebSiteUrl("URL");
            feedbackEntity1.setTypeOfPlace(TypeOfPlaceEnums.МАГАЗИН);

            feedbackEntity2 = new FeedbackEntity();

            feedbackEntity2.setFeedbackText("Тестов текст");
            feedbackEntity2.setName("name2");
            feedbackEntity2.setUrlToPic(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity2.getName() ));
            feedbackEntity2.setScore(5);
            feedbackEntity2.setWebSiteUrl("URL");
            feedbackEntity2.setTypeOfPlace(TypeOfPlaceEnums.МАГАЗИН);

            when(mockfeedbackRepository.findAll()).thenReturn(List.of(feedbackEntity1, feedbackEntity2));

            serviceToTest = new FeedbackServiceImpl(new ModelMapper(), mockfeedbackRepository,mockuserService, cloudinaryService);

        }

    @Test
    public void testFindAllFeedbacks() {


        List<FeedbackViewModel> allModels = serviceToTest.findAllFeedbacks();

        Assertions.assertEquals(2, allModels.size());

        FeedbackViewModel model1 = allModels.get(0);
        FeedbackViewModel model2 = allModels.get(1);

        // verify model 1
        Assertions.assertEquals(feedbackEntity1.getName(), model1.getName());
        Assertions.assertEquals(feedbackEntity1.getFeedbackText(), model1.getFeedbackText());
        Assertions.assertEquals(feedbackEntity1.getUrlToPic(), model1.getUrlToPic());
        Assertions.assertEquals(feedbackEntity1.getScore(), model1.getScore());
        Assertions.assertEquals(feedbackEntity1.getTypeOfPlace(), model1.getTypeOfPlace());
        Assertions.assertEquals(feedbackEntity1.getWebSiteUrl(), model1.getWebSiteUrl());

        // verify model 2
        Assertions.assertEquals(feedbackEntity2.getName(), model2.getName());
        Assertions.assertEquals(feedbackEntity2.getFeedbackText(), model2.getFeedbackText());
        Assertions.assertEquals(feedbackEntity2.getUrlToPic(), model2.getUrlToPic());
        Assertions.assertEquals(feedbackEntity2.getScore(), model2.getScore());
        Assertions.assertEquals(feedbackEntity2.getTypeOfPlace(), model2.getTypeOfPlace());
        Assertions.assertEquals(feedbackEntity2.getWebSiteUrl(), model2.getWebSiteUrl());
        }



//
//        @Test
//        public void addFeedback_should_save_new_feedback () {
//            mockfeedbackRepository.save(feedbackEntity1);
//            FeedbackAddServiceModel feedbackAddServiceModel = mockModelMapper.map(feedbackEntity1, FeedbackAddServiceModel.class);
//            serviceToTest.addFeedback(feedbackAddServiceModel);
//            Mockito.verify(mockfeedbackRepository).save(feedbackEntity1);
//
//        }
//
//    @Test
//    public void deleteFeedback_should_delete_Feedback () {
//
//        FeedbackEntity feedbackEntity3 = new FeedbackEntity();
//
//        feedbackEntity3.setFeedbackText("Тестов текст");
//        feedbackEntity3.setName("name3");
//        feedbackEntity3.setLogoPicture(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity3.getName() ));
//        feedbackEntity3.setScore(5);
//        feedbackEntity3.setWebSiteUrl("URL");
//        feedbackEntity3.setTypeOfPlace(TypeOfPlaceEnums.МАГАЗИН);
//
////        Mockito.when(mockfeedbackRepository.findById(feedbackEntity3.getId())).thenReturn(Optional.of(feedbackEntity3));
//        serviceToTest.delete(feedbackEntity3.getId());
//        Mockito.verify(mockfeedbackRepository).delete(feedbackEntity3);
//
//    }

//
//    @Test
//    void deleteLessonTest(){
//        LessonEntity lesson=new LessonEntity();
//        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
//        lesson.setLessonName("lesson");
//        lesson.setLessonUrl("lessonUrl");
//        Mockito.when(mockLessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
//        lessonService.deleteLesson(lesson.getId());
//        Mockito.verify(mockLessonRepository).delete(lesson);
//
//
//    }
//
//    LessonEntity lesson=new LessonEntity();
//        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
//        lesson.setLessonName("lesson");
//        lesson.setLessonUrl("lessonUrl");
//        mockLessonRepository.save(lesson);
//    LessonServiceModel lessonServiceModel = mockModelMapper.map(lesson, LessonServiceModel.class);
//        lessonService.addLesson(lessonServiceModel);
//        Mockito.verify(mockLessonRepository).save(lesson);



//        @Test
//        public void deleteFeedback_Should_Delete_feedback () {
//        Mockito.when(mockfeedbackRepository.findById(FEEDBACK_ID)).thenReturn(Optional.of(feedbackEntity2));
//
//        serviceToTest.delete(FEEDBACK_ID);
//        Mockito.verify(mockfeedbackRepository,times(1)).delete(feedbackEntity2);
//
//        }

//
//    @org.junit.Test
//    public void deleteCity_Should_ExecuteCorrectly() {
//        Mockito.when(mockCityRepository.findById(CITY_ID))
//                .thenReturn(Optional.of(city));
//
//        cityService.deleteCity(CITY_ID);
//
//        Mockito.verify(mockCityRepository, times(1)).delete(city);
//    }

//    @Test
//    public void addFeedback_Should_Save_Feedback() {
//        feedbackAddServiceModel = new FeedbackAddServiceModel();
//
//        feedbackAddServiceModel.setFeedbackText("Тестов текст");
//        feedbackAddServiceModel.setName("neme1");
//        feedbackAddServiceModel.setLogoPicture(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity1.getName() ));
//        feedbackAddServiceModel.setScore(5);
//        feedbackAddServiceModel.setWebSiteUrl("URL");
//        feedbackAddServiceModel.setTypeOfPlace(TypeOfPlaceEnums.МАГАЗИН);
//
//
////        feedbackEntity1 = new FeedbackEntity();
////
////        feedbackEntity1.setFeedbackText("Тестов текст");
////        feedbackEntity1.setName("neme1");
////        feedbackEntity1.setLogoPicture(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity1.getName() ));
////        feedbackEntity1.setScore(5);
////        feedbackEntity1.setWebSiteUrl("URL");
////        feedbackEntity1.setTypeOfPlace(TypeOfPlaceEnums.МАГАЗИН);
//
//         serviceToTest.addFeedback(feedbackAddServiceModel);
//        List<FeedbackViewModel> allModels = serviceToTest.findAllFeedbacks();
//        Assertions.assertEquals(3, allModels.size());
//
//    }



}