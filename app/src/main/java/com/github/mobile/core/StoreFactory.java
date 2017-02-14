package com.github.mobile.core;

import com.github.mobile.core.commit.CommitStore;
import com.github.mobile.core.gist.GistStore;
import com.github.mobile.core.issue.IssueStore;

import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.PullRequestService;

/**
 * Created by XiaowenMao on 2017/2/13.
 */

public class StoreFactory {

    public CommitStore newStore(final CommitService service) {
        return new CommitStore(service);
    }

    public GistStore newStore(final GistService service) {
        return new GistStore(service);
    }

    public IssueStore newStore(final IssueService issueService,
                               final PullRequestService pullService) {
        return new IssueStore(issueService, pullService);
    }
}
