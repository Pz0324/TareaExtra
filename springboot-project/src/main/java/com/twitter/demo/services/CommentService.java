package com.twitter.demo.services;

import com.twitter.demo.entities.Comments;
import com.twitter.demo.entities.Post;
import com.twitter.demo.entities.User;
import com.twitter.demo.entities.dto.CreateCommentDto;
import com.twitter.demo.entities.dto.CommentsDto;
import com.twitter.demo.repositories.CommentsRepository;
import com.twitter.demo.repositories.PostRepository;
import com.twitter.demo.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class CommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void createComment(CreateCommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (optionalPost.isEmpty()) {
            throw new RuntimeException("Post not found with id: " + commentDto.getPostId());
        }
        Post post = optionalPost.get();
        Comments comment = new Comments();
        comment.setMessage(commentDto.getComment());
        comment.setPost(post);

        User author = userRepository.findById(commentDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setAuthor(author);

        commentsRepository.save(comment);
    }

    public List<CommentsDto> getUserComments(UUID userId){
        List<CommentsDto> response = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        List<Comments> comments = commentsRepository.findByAuthorId(userId);
        comments.forEach(comment -> {
            response.add(new CommentsDto(comment.getId(),comment.getMessage()));
        });
        return response;
    }

    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }


}
