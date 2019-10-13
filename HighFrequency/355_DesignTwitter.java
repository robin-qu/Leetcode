// // Initial version
// class Twitter {
//     private class Feed {
//         public int userId;
//         public int tweetId;
        
//         public Feed(int userId, int tweetId) {
//             this.userId = userId;
//             this.tweetId = tweetId;
//         }
//     }
    
//     private Map<Integer, Set<Integer>> follows;
//     private List<Feed> feeds;

//     /** Initialize your data structure here. */
//     public Twitter() {
//         this.follows = new HashMap<>();
//         this.feeds = new ArrayList<>();
//     }
    
//     /** Compose a new tweet. */
//     public void postTweet(int userId, int tweetId) {
//         if (!follows.containsKey(userId)) {
//             follows.put(userId, new HashSet<>());
//             follows.get(userId).add(userId);
//         }
//         feeds.add(new Feed(userId, tweetId));
//     }
    
//     /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
//     public List<Integer> getNewsFeed(int userId) {
//         List<Integer> res = new ArrayList<>();
//         for (int i = feeds.size() - 1; i >= 0; i--) {
//             Feed curr = feeds.get(i);
//             if (follows.containsKey(userId) && follows.get(userId).contains(curr.userId)) {
//                 res.add(curr.tweetId);
//                 if (res.size() == 10) {
//                     break;
//                 }
//             }
//         }
        
//         return res;
//     }
    
//     /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
//     public void follow(int followerId, int followeeId) {
//         if (!follows.containsKey(followerId)) {
//             follows.put(followerId, new HashSet<>());
//             follows.get(followerId).add(followerId);
//         }
//         follows.get(followerId).add(followeeId);
//     }
    
//     /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
//     public void unfollow(int followerId, int followeeId) {
//         if (followerId == followeeId) {
//             return;
//         }
//         if (follows.containsKey(followerId) && follows.get(followerId).contains(followeeId)) {
//             follows.get(followerId).remove(followeeId);
//         }
//     }
// }

// /**
//  * Your Twitter object will be instantiated and called as such:
//  * Twitter obj = new Twitter();
//  * obj.postTweet(userId,tweetId);
//  * List<Integer> param_2 = obj.getNewsFeed(userId);
//  * obj.follow(followerId,followeeId);
//  * obj.unfollow(followerId,followeeId);
//  */


// // OOD
// class Twitter {
//     private int timestamp;  // records the order of the posts
//     private Map<Integer, User> users;  // userId -> User object
    
//     private class Tweet implements Comparable<Tweet> {
//         public int tweetId;
//         public int time;   // the relative time the tweet was posted
//         public Tweet next;  // the next post
        
//         public Tweet(int tweetId) {
//             this.tweetId = tweetId;
//             this.time = timestamp++;
//             this.next = null;
//         }
        
//         @Override
//         public int compareTo(Tweet other) {  // sort the time descendingly
//             return other.time - this.time;
//         }
//     }
    
//     private class User {
//         public int userId;
//         public Set<Integer> follows;   // the users she follows
//         public Tweet head;   // the tweets she posts
        
//         public User(int userId) {
//             this.userId = userId;
//             this.follows = new HashSet<>();
//             this.follows.add(userId);  // follows herself when create the user
//             this.head = null;
//         }
        
//         // posts a new tweet
//         public void post(int tweetId) {
//             Tweet newPost = new Tweet(tweetId);
//             newPost.next = this.head;
//             this.head = newPost;  // add to the front of tweet list
//         }
        
//         // follows a user
//         public void follow(int followeeId) {
//             follows.add(followeeId);
//         }
        
//         // unfollows a user if she already follows the user
//         public void unfollow(int followeeId) {
//             follows.remove(followeeId);
//         }
//     }

//     /** Initialize your data structure here. */
//     public Twitter() {
//         this.timestamp = 0;
//         this.users = new HashMap<>();
//     }
    
//     /** Compose a new tweet. */
//     public void postTweet(int userId, int tweetId) {
//         if (!users.containsKey(userId)) {
//             users.put(userId, new User(userId));
//         }
//         users.get(userId).post(tweetId);
//     }
    
//     /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
//     public List<Integer> getNewsFeed(int userId) {
//         PriorityQueue<Tweet> pq = new PriorityQueue<>();
//         if (users.containsKey(userId)) {
//             for (int id : users.get(userId).follows) {
//                 User user = users.get(id);
//                 if (user.head != null) {
//                     pq.offer(user.head);
//                 }
//             }
//         }
        
//         List<Integer> res = new ArrayList<>();
//         while (!pq.isEmpty() && res.size() < 10) {
//             Tweet curr = pq.poll();
//             res.add(curr.tweetId);
//             if (curr.next != null) {
//                 pq.offer(curr.next);
//             }
//         }
        
//         return res;
//     }
    
//     /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
//     public void follow(int followerId, int followeeId) {
//         // check if follower exists
//         if (!users.containsKey(followerId)) {
//             users.put(followerId, new User(followerId));
//         }
//         // check if followee exists
//         if (!users.containsKey(followeeId)) {
//             users.put(followeeId, new User(followeeId));
//         }
        
//         users.get(followerId).follow(followeeId);
//     }
    
//     /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
//     public void unfollow(int followerId, int followeeId) {
//         if (!users.containsKey(followerId) || followerId == followeeId) {
//             return;
//         }
        
//         users.get(followerId).unfollow(followeeId);
//     }
// }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */


class Twitter {
    class User {
        public int id;
        public Set<Integer> follows;
        public Tweet tweets;
        
        public User(int id) {
            this.id = id;
            this.follows = new HashSet<>();
            this.follows.add(this.id);
            this.tweets = null;
        }
        
        public void follow(int followeeID) {
            follows.add(followeeID);
        }
        
        public void unfollow(int followeeID) {
            if (followeeID == this.id) {
                return;
            }
            follows.remove(followeeID);
        }
        
        public void postTweet(int tweetId) {
            Tweet newTweet = new Tweet(tweetId);
            newTweet.next = this.tweets;
            this.tweets = newTweet;
        }
    }
    
    class Tweet {
        public int id;
        public int time;
        public Tweet next;
        
        public Tweet(int id) {
            this.id = id;
            this.time = timestamp++;
            this.next = null;
        }
    }
    
    private Map<Integer, User> users;  // userID --> user object
    private int timestamp;

    /** Initialize your data structure here. */
    public Twitter() {
        this.users = new HashMap<>();
        this.timestamp = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId));
        }
        
        users.get(userId).postTweet(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId));
            return new ArrayList<>();
        }
        
        PriorityQueue<Tweet> pq = new PriorityQueue<>(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet t1, Tweet t2) {
                return t2.time - t1.time;
            }
        });
        
        for (int followId : users.get(userId).follows) {
            if (users.get(followId).tweets != null) {
                pq.offer(users.get(followId).tweets);
            }
        }
        
        List<Integer> res = new ArrayList<>();
        while (res.size() < 10 && !pq.isEmpty()) {
            Tweet curr = pq.poll();
            res.add(curr.id);
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!users.containsKey(followerId)) {
            users.put(followerId, new User(followerId));
        }
        if (!users.containsKey(followeeId)) {
            users.put(followeeId, new User(followeeId));
        }
        users.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!users.containsKey(followerId)) {
            users.put(followerId, new User(followerId));
        }
        if (!users.containsKey(followeeId)) {
            users.put(followeeId, new User(followeeId));
        }
        
        users.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */