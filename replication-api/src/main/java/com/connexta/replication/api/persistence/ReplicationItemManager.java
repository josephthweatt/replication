/**
 * Copyright (c) Connexta
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package com.connexta.replication.api.persistence;

import com.connexta.replication.api.NodeAdapter;
import com.connexta.replication.api.data.Filter;
import com.connexta.replication.api.data.Metadata;
import com.connexta.replication.api.data.ReplicationItem;
import java.util.List;
import java.util.Optional;

public interface ReplicationItemManager extends DataManager<ReplicationItem> {

  /**
   * If present, returns the latest {@link ReplicationItem} (based on {@link
   * ReplicationItem#getDoneTime()}) for the given {@link Metadata}.
   *
   * @param filterId id for the {@link Filter} which the item belongs to
   * @param metadataId a unique metadata id
   * @return an optional containing the item, or an empty optional if there was an error fetching
   *     the item or it was not found.
   */
  Optional<ReplicationItem> getLatest(String filterId, String metadataId);

  /**
   * Returns a list of {@code ReplicationItem}s associated with a {@link Filter}.
   *
   * @param filterId unique id for the {@link Filter}
   * @param startIndex index to start query at
   * @param pageSize max number of results to return in a single query
   * @return list of items for the given {@link Filter} id
   * @throws com.connexta.replication.api.data.ReplicationPersistenceException if there is an error
   *     fetching the items
   */
  List<ReplicationItem> getAllForFilter(String filterId, int startIndex, int pageSize);

  /**
   * Get the list of IDs for {@link ReplicationItem}s that failed to be transferred between the
   * source and destination {@link NodeAdapter}s.
   *
   * @param filterId the {@link Filter} id to get failures for
   * @return list of ids for items that failed to be transferred
   */
  List<String> getFailureList(String filterId);

  /**
   * Deletes all the items for a {@link Filter}.
   *
   * @param filterId id of the {@link Filter}
   * @throws com.connexta.replication.api.data.ReplicationPersistenceException if there was an error
   *     deleting the items
   */
  void removeAllForFilter(String filterId);
}
