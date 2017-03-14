package com.github.mobile.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.github.mobile.R;
import com.github.mobile.api.model.Issue;
import com.github.mobile.api.model.Notification;
import com.github.mobile.api.model.Repository;
import com.github.mobile.core.issue.IssueFilter;
import com.github.mobile.ui.comment.CommentListAdapter;
import com.github.mobile.ui.commit.CommitFileListAdapter;
import com.github.mobile.ui.commit.CommitListAdapter;
import com.github.mobile.ui.commit.DiffStyler;
import com.github.mobile.ui.gist.GistListAdapter;
import com.github.mobile.ui.issue.DashboardIssueListAdapter;
import com.github.mobile.ui.issue.EventListAdapter;
import com.github.mobile.ui.issue.FilterListAdapter;
import com.github.mobile.ui.issue.IssueFragment;
import com.github.mobile.ui.issue.RepositoryIssueListAdapter;
import com.github.mobile.ui.issue.SearchIssueListAdapter;
import com.github.mobile.ui.notification.NotificationsListAdapter;
import com.github.mobile.ui.repo.ContributorListAdapter;
import com.github.mobile.ui.repo.DefaultRepositoryListAdapter;
import com.github.mobile.ui.repo.UserRepositoryListAdapter;
import com.github.mobile.ui.search.SearchRepositoryListAdapter;
import com.github.mobile.ui.search.SearchUserListAdapter;
import com.github.mobile.ui.team.TeamListAdapter;
import com.github.mobile.ui.user.NewsListAdapter;
import com.github.mobile.ui.user.UserListAdapter;
import com.github.mobile.util.AvatarLoader;
import com.github.mobile.util.HttpImageGetter;


import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchIssue;
import org.eclipse.egit.github.core.Team;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by XiaowenMao on 2017/2/13.
 */

public class ListAdapterFactory {

    // user
    public UserListAdapter newListAdapter(final LayoutInflater inflater,
                                          final User[] elements,
                                          final AvatarLoader avatars) {
        return new UserListAdapter(inflater, elements, avatars);
    }
    
    
    // news
    public NewsListAdapter newListAdapter(LayoutInflater inflater,
                                          Event[] elements,
                                          AvatarLoader avatars) {
        return new NewsListAdapter(inflater, elements, avatars);
    }

    public NewsListAdapter newListAdapter(LayoutInflater inflater,
                                          Event[] elements,
                                          AvatarLoader avatars,
                                          boolean showRepoName) {
        return new NewsListAdapter(inflater, elements, avatars, showRepoName);
    }

    public NewsListAdapter newListAdapter(LayoutInflater inflater,
                                           AvatarLoader avatars) {
        return new NewsListAdapter(inflater, avatars);
    }

    // team
    public TeamListAdapter newListAdapter(final LayoutInflater inflater,
                                          final Team[] elements) {
        return new TeamListAdapter(inflater, elements);
    }

    // search
    public SearchUserListAdapter newListAdapter(final Context context,
                                                 final com.github.mobile.api.model.User[] elements,
                                                 final AvatarLoader avatars) {
        return new SearchUserListAdapter(context, elements, avatars);
    }

    public SearchRepositoryListAdapter newListAdapter (LayoutInflater inflater,
                                                        Repository[] elements) {
        return new SearchRepositoryListAdapter(inflater, elements);
    }

    // repo
    public UserRepositoryListAdapter newListAdapter(LayoutInflater inflater,
                                                     org.eclipse.egit.github.core.Repository[] elements,
                                                     User user) {
        return new UserRepositoryListAdapter(inflater, elements, user);
    }

    public DefaultRepositoryListAdapter newListAdapter(LayoutInflater inflater,
                                        org.eclipse.egit.github.core.Repository[] elements,
                                        AtomicReference<User> account) {
        return new DefaultRepositoryListAdapter(inflater, elements, account);
    }

    public ContributorListAdapter newListAdapter(final Context context,
                                  final Contributor[] elements, final AvatarLoader avatars) {
        return new ContributorListAdapter(context, elements, avatars);
    }

    // notification
    public NotificationsListAdapter newListAdapter(Activity activity, Notification[] elements) {
        return new NotificationsListAdapter(activity, elements);
    }

    // issue
    public DashboardIssueListAdapter newListAdapter(AvatarLoader avatars,
                                     Resources resources, LayoutInflater inflater, Issue[] elements) {
        return new DashboardIssueListAdapter(avatars, resources, inflater, elements);
    }

    public EventListAdapter newListAdapter(Activity activity, AvatarLoader avatars,
                            HttpImageGetter imageGetter, IssueFragment issueFragment,
                            boolean isCollaborator, String loggedUser) {
        return new EventListAdapter(activity, avatars, imageGetter, issueFragment, isCollaborator, loggedUser);
    }

    public FilterListAdapter newListAdapter(LayoutInflater inflater, IssueFilter[] elements,
                             AvatarLoader avatars) {
        return new FilterListAdapter(inflater, elements, avatars);
    }

    public RepositoryIssueListAdapter newListAdapter(LayoutInflater inflater,
                                                      Resources resources,
                                                      org.eclipse.egit.github.core.Issue[] elements,
                                                      AvatarLoader avatars) {
        return new RepositoryIssueListAdapter(inflater, resources, elements, avatars);
    }

    public SearchIssueListAdapter newListAdapter(LayoutInflater inflater,
                                                  Resources resources,
                                                  SearchIssue[] elements,
                                                  AvatarLoader avatars) {
        return new SearchIssueListAdapter(inflater, resources, elements, avatars);
    }

    // gist
    public GistListAdapter newListAdapter(AvatarLoader avatars, Resources resources,
                                           Activity activity, Collection<Gist> elements) {
        return new GistListAdapter(avatars, resources, activity, elements);
    }

    // commit
    public CommitFileListAdapter newListAdapter(final LayoutInflater inflater,
                                 final DiffStyler diffStyler,
                                 final AvatarLoader avatars,
                                 final HttpImageGetter imageGetter) {
        return new CommitFileListAdapter(inflater, diffStyler, avatars, imageGetter);
    }

    public CommitListAdapter newListAdapter(int viewId, LayoutInflater inflater,
                                            Resources resources,
                                            Collection<RepositoryCommit> elements,
                                            AvatarLoader avatars) {
        return new CommitListAdapter(viewId, inflater, resources, elements, avatars);
    }

    // comment
    public CommentListAdapter newListAdapter(LayoutInflater inflater,
                                             AvatarLoader avatars,
                                             HttpImageGetter imageGetter) {
        return new CommentListAdapter(inflater, avatars, imageGetter);
    }

}







